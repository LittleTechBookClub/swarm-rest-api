This is the Swarm app backend REST API, a Spring Web application.

Requirements:

Java - https://www.java.com/en/download/help/download_options.html (Recommend using Java 17 or higher)
Gradle - https://gradle.org/install/

Run `./gradlew build`
To check the running service, use `./gradlew bootRun`
You should be able to curl or check this url in browser: http://localhost:8080
and see "Hello Swarm!"

Unit Tests:



OpenAI API Call Example:

*IMPORTANT*
Create an application.yml in the src/main/resources folder(the one with application.properties) AND another application.yml in test/resources. Then get an OpenAI api key from their website
and copy it into both of the application.yml files in this format:
OPENAI_API_KEY: <YOUR-GENERATED-KEY-GOES-HERE>

ABSOLUTELY DO NOT COPY THIS KEY INTO ANY OTHER FILE OR COMMIT CODE WITH KEYS IN IT.
