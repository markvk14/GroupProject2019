@echo off
echo please be patient
echo ----------------------------------------------------
rem activeer de drive van workspace
%~d0
rem ga naar de huidige directory: dwz <workspace>\webapp
cd %~dp0
rem ga naar de directory van index.html: dwz <workspace>\webapp\app
cd app
call polymer lint 
echo off
echo on-demand loaded elements (not using static import) 
echo ..moeten in polymer.json in de paragraaf "fragments" staan
echo ..foute info in polymer.json leidt tot "lint" fouten 
echo ..betreffende missende import of file's not found
pause


