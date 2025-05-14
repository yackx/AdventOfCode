import Data.List

hitsToDefeat health damage = health `quot'` max 1 damage
  where
    h `quot'` d = (h-1) `quot` d + 1

-- (cost,(damage,armor))
weapons = zip[8,10,25,40,74] $ zip [4,5,6,7,8] [0,0,0,0]
armors = zip[13,31,53,75,102] $ zip [0,0,0,0] [1,2,3,4]
rings = zip[25,50,100,20,40,80] $ zip [1,2,3,0,0,0] [0,0,0,1,2,3]

armorChoices = (0,(0,0)) : armors
ringChoices = filter((<=2).length) $ subsequences rings

payload = [ [w,a]++r
  | w <- weapons
  , a <- armorChoices
  , r <- ringChoices ]

cost = sum . map fst
damage = sum . map (fst . snd)
armor = sum . map (snd . snd)

hp = 100
bossHp = 104
bossDamage = 8
bossArmor = 1

validGear gear =
  hitsToDefeat bossHp (damage gear - bossArmor) <= hitsToDefeat hp (bossDamage - armor gear)

solution = minimum . map cost . filter validGear $ payload

main :: IO ()
main = do
  print solution

