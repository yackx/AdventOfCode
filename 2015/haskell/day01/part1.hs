findDestination :: String -> Int
findDestination input =
  let up = length $ filter (== '(') input
      down = length $ filter (== ')') input
  in up - down

main :: IO ()
main = do
  input <- getContents
  let trimmed = filter (/= '\n') input
  print $ findDestination trimmed