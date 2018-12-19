javac -d C:\Users\Elisabeth\git\2018\Devoir-Algav C:\Users\Elisabeth\git\2018\Devoir-Algav\src\Key.java C:\Users\Elisabeth\git\2018\Devoir-Algav\src\TasMinArbreBinaire.java C:\Users\Elisabeth\git\2018\Devoir-Algav\src\ComplexiteTasMinArbreBinaire.java C:\Users\Elisabeth\git\2018\Devoir-Algav\src\Main.java
REM for %%f in (1, 2, 3, 4, 5) do (for /l %%g in (%%f, 1, 5) do (
for %%x in (100, 200, 500, 1000, 5000, 10000, 20000, 50000) do (
for /l %%i in (0, 1, 29) do (
java -classpath C:\Users\Elisabeth\git\2018\Devoir-Algav Main %%x 1 2 >> "resalgav12.txt")
REM )
echo "" >> "resalgav12.txt" )
REM ))
pause