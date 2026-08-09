@echo off
call gradlew.bat build
xcopy "build\libs\*.jar" "%APPDATA%\.minecraft\mods\" /Y
pause