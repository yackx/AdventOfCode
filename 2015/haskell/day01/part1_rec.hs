findDestination :: String -> Int
findDestination input = foldl step 0 input
  where
    step :: Int -> Char -> Int
    step floor '(' = floor + 1
    step floor ')' = floor - 1
    step _ invalid = error "Invalid character in input"

main :: IO ()
main = do
  input <- getContents
  let trimmed = filter (/= '\n') input
  print $ findDestination trimmed