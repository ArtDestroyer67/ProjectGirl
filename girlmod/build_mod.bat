@echo off
git pull
set GRADLE_USER_HOME=F:\gradle_home
call gradlew.bat build
xcopy "build\libs\*.jar" "%APPDATA%\.minecraft\mods\" /Y
pause