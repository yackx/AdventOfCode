#include <iostream>
#include <iterator>
#include <string>
#include <regex>
#include <map>
#include <algorithm>

#ifdef DEBUG
    #define LOG cout
#else
    #define LOG if (false) std::cout
#endif

using LINE = std::tuple<int, int, int, int>;

enum Part { one, two };

std::vector<LINE> read_cin()
{
    std::vector<LINE> lines;
    std::string line;
    const std::regex rx("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)");
    while (std::getline(std::cin, line) && !line.empty()) {
        LOG << line << std::endl;
        std::smatch match;
        if (std::regex_match(line, match, rx)) {
            lines.push_back(std::make_tuple(
                std::stoi(match.str(1)),
                std::stoi(match.str(2)),
                std::stoi(match.str(3)),
                std::stoi(match.str(4))
            ));
        } else {
            std::cerr << "Invalid line: " << line << std::endl;
            exit(1);
        }
    }

    return lines;
}

long solve(std::vector<LINE> lines, Part part)
{
    std::regex rx("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)");
    std::map<std::pair<int, int>, int> diagram;  // k=point coord; v=nb of points
    for (std::tuple<int, int, int, int> line : lines) {
        std::smatch match;
        auto [x1, y1, x2, y2] = line;
        if (x1 == x2) {
            // vertical
            auto[min, max] = std::minmax(y1, y2);
            for (int y = min; y <= max; y++) {
                diagram[std::make_pair(x1, y)]++;
            }
        } else if (y1 == y2) {
            // horizontal
            auto[min, max] = std::minmax(x1, x2);
            for (int x = min; x <= max; x++) {
                diagram[std::make_pair(x, y1)]++;
            }
        } else if (part == two) {
            // check diagonals
            bool downwardDiagonal = x1 - x2 == y1 - y2;
            bool upwardDiagonal = x1 - x2 == -(y1 - y2);
            if (downwardDiagonal || upwardDiagonal) {
                int deltaY = downwardDiagonal ? +1 : -1;  // y goes down or up
                LOG << "d   " << x1 << "," << y1 << " - " << x2 << "," << y2 << std::endl;
                if (x1 > x2) {
                    // left to right only
                    std::swap(x1, x2);
                    std::swap(y1, y2);
                    LOG << "d*  " << x1 << "," << y1 << " - " << x2 << "," << y2 << std::endl;
                }
                for (int x = x1, y = y1; x <= x2; x++, y += deltaY) {
                    LOG << "  " << x << "," << y << std::endl;
                    diagram[std::make_pair(x, y)]++;
                }
            }
        }
    }

    long score = count_if(diagram.begin(), diagram.end(), [](auto p){return p.second >= 2;});
    return score;
}


int main()
{
    auto lines = read_cin();
    std::cout << solve(lines, one) << std::endl;
    std::cout << solve(lines, two) << std::endl;
}
