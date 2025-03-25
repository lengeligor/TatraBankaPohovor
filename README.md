# CurrencyApp

## Domain vocabulary
Valuta = hotovosť v cudzej mene (bankovky a mince).

Deviza = bezhotovostné prostriedky v cudzej mene (prevody, účty, platby).

## Docker build & run
docker build -t tatrabanka:latest .
docker run -p 8085:8085 --name tatrabanka tatrabanka:latest