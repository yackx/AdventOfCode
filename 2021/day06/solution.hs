import System.Environment

main :: IO()
main = do
    input <- getLine
    let solution = solve . inputToFishes $ input
    putStrLn(show solution)

solve :: [Int] -> Int
solve fishes = length (iterate nextGen fishes !! 80)

nextGen :: [Int] -> [Int]
nextGen fishes = (incrementPool fishes) ++ (newSpawns fishes)

incrementPool :: [Int] -> [Int]
incrementPool fishes = [if x == 0 then 6 else x - 1 | x <- fishes]

newSpawns :: [Int] -> [Int]
newSpawns fishes = replicate (countSpawn fishes) $ 8

countSpawn :: [Int] -> Int
countSpawn fishes = length . filter (==0) $ fishes

inputToFishes :: String -> [Int]
inputToFishes input = map read . split $ input :: [Int]

-- Could use Data.List.Split
-- https://hackage.haskell.org/package/split-0.2.3.5/docs/Data-List-Split.html
split :: String -> [String]
split str = case break(==',') str of
    (a, _comma : b) -> a : split b
    (a, _empty)     -> [a]
