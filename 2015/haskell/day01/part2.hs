findBasement :: String -> Int
findBasement input = aux 0 0
  where
    aux :: Int -> Int -> Int
    aux index (-1) = index
    aux index floor
      | index >= length input = error "No basement found"
      | otherwise = aux (index + 1) nextFloor
        where
          nextFloor = case input !! index of
            '(' -> floor + 1
            ')' -> floor - 1
            _ -> error "Invalid character in input"

main :: IO ()
main = do
  input <- getContents
  print $ findBasement input
