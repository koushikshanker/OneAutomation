# OneAutomation

1. Install Java
brew install openjdk@17

2. Install maven
brew install maven

mvn clean install -U -DskipTests

mvn --global-settings settings.xml clean install -U -DskipTests

mvn test -Dcucumber.filter.tags="@gitpr"

mvn --global-settings settings.xml clean install -Dcucumber-filter.tags="@sanity"