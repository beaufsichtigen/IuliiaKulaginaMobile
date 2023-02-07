# IuliiaKulagina Mobile tests

Tests could be run with maven commands:
mvn clean test -P native  for both native app tests
mvn clean test -P web  for both web app tests

Before run:

1. Please set maven environment variables with names
"email",
"username",
"password".


2. Please add OS system variables:
- USERNAMEMOBIT. For example if you registration email epam_epamov@epam.com, use epam_epamov.  
- TOKEN variable with your personal token from mobitru.

3. You have to take device and install native app before running tests

4. For web test please use UA VPN cookie policy if needed.
