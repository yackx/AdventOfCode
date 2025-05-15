package main

import (
	"fmt"
	"math"
)

type Boss struct {
	Hp     int
	Damage int
}

type Player struct {
	Hp           int
	Mana         int
	ManaSpent    int
	ActiveSpells map[SpellType]int
	Armor        int
}

type Game struct {
	Player       *Player
	Boss         *Boss
	IsPlayerTurn bool
	Turn         int
}

type SpellType int

const (
	MagicMissile SpellType = iota
	Drain
	Shield
	Poison
	Recharge
)

func NewGame(playerHp, playerMana, bossHp, bossDamage int) *Game {
	return &Game{
		Player: &Player{
			Hp:           playerHp,
			Mana:         playerMana,
			ManaSpent:    0,
			ActiveSpells: make(map[SpellType]int),
			Armor:        0,
		},
		Boss: &Boss{
			Hp:     bossHp,
			Damage: bossDamage,
		},
		IsPlayerTurn: true,
	}
}

// AddSpell adds a spell to the player.
// Returns true if the spell was added,
// false if it was already active or not enough mana
func (p *Player) AddSpell(spellType SpellType) bool {
	if _, ok := p.ActiveSpells[spellType]; ok {
		return false
	}
	costs := map[SpellType]int{
		MagicMissile: 53,
		Drain:        73,
		Shield:       113,
		Poison:       173,
		Recharge:     229,
	}
	cost := costs[spellType]
	if p.Mana < cost {
		return false
	}
	p.Mana -= cost
	p.ManaSpent += cost
	durations := map[SpellType]int{
		MagicMissile: -1,
		Drain:        -1,
		Shield:       6,
		Poison:       6,
		Recharge:     5,
	}
	p.ActiveSpells[spellType] = durations[spellType]
	return true
}

func (p *Player) Copy() *Player {
	player := &Player{
		Hp:           p.Hp,
		Mana:         p.Mana,
		ManaSpent:    p.ManaSpent,
		ActiveSpells: make(map[SpellType]int),
		Armor:        p.Armor,
	}
	for spellType, duration := range p.ActiveSpells {
		player.ActiveSpells[spellType] = duration
	}
	return player
}

func (b *Boss) Copy() *Boss {
	return &Boss{
		Hp:     b.Hp,
		Damage: b.Damage,
	}
}

func (g *Game) Copy() *Game {
	return &Game{
		Player:       g.Player.Copy(),
		Boss:         g.Boss.Copy(),
		IsPlayerTurn: g.IsPlayerTurn,
		Turn:         g.Turn,
	}
}

func (b *Boss) IsAlive() bool {
	return b.Hp > 0
}

func (p *Player) IsAlive() bool {
	return p.Hp > 0 && p.Mana > 0
}

func (p *Player) String() string {
	abbrev := map[SpellType]string{
		MagicMissile: "M",
		Drain:        "D",
		Shield:       "S",
		Poison:       "P",
		Recharge:     "R",
	}
	spells := ""
	for i := MagicMissile; i <= Recharge; i++ {
		if _, ok := p.ActiveSpells[i]; ok {
			spells += abbrev[i]
		} else {
			spells += "."
		}
	}

	return fmt.Sprintf(
		"P[(%d/%d) |%s|  m=%d (%d)]",
		p.Hp, p.Armor, spells, p.Mana, p.ManaSpent)
}

func (b *Boss) String() string {
	return fmt.Sprintf("B[(%d/%d)]", b.Hp, b.Damage)
}

func (g *Game) String() string {
	return fmt.Sprintf("#%d %s %s P?=%v", g.Turn, g.Player, g.Boss, g.IsPlayerTurn)
}

// Prune graph when player dies (obviously) or
//if there exists a win with less mana spent

const DeadPlayer = math.MaxInt
const TooMuchMana = math.MaxInt

// Fight simulates the game.
// manaLimit is the maximum mana that can be spent (prune)
// hard is true if difficulty is set to hard (part2: player loses 1 hp at start)
// returns the mana spent if the player wins,
// or DeadPlayer if the player dies,
// or TooMuchMana if the mana limit is reached (another, less expensive win exists)
func (g *Game) Fight(manaLimit int, hard bool) int {
	g.Turn++

	if hard && g.IsPlayerTurn {
		g.Player.Hp--
	}

	// Check if mana limit is reached
	if g.Player.ManaSpent >= manaLimit {
		return TooMuchMana
	}

	// Check if game is over
	if !g.Player.IsAlive() {
		return DeadPlayer
	}
	if !g.Boss.IsAlive() {
		return g.Player.ManaSpent
	}

	// Apply effects (not instants)
	keepSpells := make(map[SpellType]int)
	for spellType, duration := range g.Player.ActiveSpells {
		if duration > 0 {
			switch spellType {
			case Poison:
				g.Boss.Hp -= 3
			case Shield:
				g.Player.Armor = 7
			case Recharge:
				g.Player.Mana += 101
			default: // No effect
			}
		}
		if duration > 1 {
			keepSpells[spellType] = duration - 1
		}
	}
	if _, shield := g.Player.ActiveSpells[Shield]; !shield {
		g.Player.Armor = 0
	}
	g.Player.ActiveSpells = keepSpells

	// Boss killed by effects?
	if !g.Boss.IsAlive() {
		return g.Player.ManaSpent
	}

	if g.IsPlayerTurn {
		// Test each spell to continue fighting
		var lowestMana = manaLimit
		for spell := MagicMissile; spell <= Recharge; spell++ {
			newGame := g.Copy()
			if newGame.Player.AddSpell(spell) {
				// Apply direct damages
				switch spell {
				case MagicMissile:
					newGame.Boss.Hp -= 4
				case Drain:
					newGame.Boss.Hp -= 2
					newGame.Player.Hp += 2
				default: // Not a direct action
				}
				newGame.IsPlayerTurn = false
				worth := newGame.Fight(lowestMana, hard)
				if worth < lowestMana {
					lowestMana = worth
				}
			}
		}
		return lowestMana
	} else {
		g.Player.Hp -= max(1, g.Boss.Damage-g.Player.Armor)
		if g.Player.Hp <= 0 {
			return DeadPlayer
		}
		newGame := g.Copy()
		newGame.IsPlayerTurn = true
		return newGame.Fight(manaLimit, hard)
	}
}

func main() {
	for _, difficulty := range []bool{false, true} {
		//game := NewGame(10, 250, 14, 8)
		//game := NewGame(10, 250, 13, 8)
		game := NewGame(50, 500, 71, 10)
		println(game.Fight(math.MaxInt, difficulty))
	}
}
