data Register = A | B deriving (Show, Eq)

data Instruction
  = Hlf Register
  | Tpl Register
  | Inc Register
  | Jmp Int
  | Jie Register Int
  | Jio Register Int
  deriving (Show, Eq)

parseRegister :: String -> Register
parseRegister "a" = A
parseRegister "b" = B
parseRegister _ = error "Invalid register"

parseInstruction :: String -> Instruction
parseInstruction str =
  case words' of
    ["hlf", reg] -> Hlf (parseRegister reg)
    ["tpl", reg] -> Tpl (parseRegister reg)
    ["inc", reg] -> Inc (parseRegister reg)
    ["jmp", offset] -> Jmp (read offset)
    ["jie", reg, offset] -> Jie (parseRegister reg) (read offset)
    ["jio", reg, offset] -> Jio (parseRegister reg) (read offset)
    _ -> error $ "Invalid instruction: " ++ str
  where
    cleanStr = filter (`notElem` "+,") str
    words' = words cleanStr

parseProgram :: FilePath -> IO [Instruction]
parseProgram = fmap (map parseInstruction . lines) . readFile

runProgram :: [Instruction] -> Int -> (Int, Int) -> (Int, Int)
runProgram program pc (regA, regB)
  | pc < 0 || pc >= length program = (regA, regB)
  | otherwise =
      case program !! pc of
        Hlf A -> run nextPc (regA `div` 2, regB)
        Hlf B -> run nextPc (regA, regB `div` 2)
        Tpl A -> run nextPc (regA*3, regB)
        Tpl B -> run nextPc (regA, regB*3)
        Inc A -> run nextPc (regA + 1, regB)
        Inc B -> run nextPc (regA, regB + 1)
        Jmp offset -> run (pc + offset) (regA, regB)
        Jie A offset -> run (pc + if even regA then offset else 1) (regA, regB)
        Jie B offset -> run (pc + if even regB then offset else 1) (regA, regB)
        Jio A offset -> run (pc + if regA == 1 then offset else 1) (regA, regB)
        Jio B offset -> run (pc + if regB == 1 then offset else 1) (regA, regB)
      where
        run = runProgram program
        nextPc = pc + 1

main :: IO ()
main = do
  input <- getContents
  let program = map parseInstruction (lines input)
  let (_, finalRegB0) = runProgram program 0 (0, 0)
  putStrLn $ show finalRegB0
  let (_, finalRegB1) = runProgram program 0 (1, 0)
  putStrLn $ show finalRegB1