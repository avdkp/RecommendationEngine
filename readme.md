# Recommendation Engine

I take user and Restaurant array and generate the sorted recommendation beased of a list of rules.
Exact problem statement here: [doc](https://docs.google.com/document/d/1RjHpFN_jgWiKxaw3Ulws9nAauVknJCqfes0497rUido/edit)

## Setup
Tested environment
* Java 1.8
* Gradle 7.5.1

1. To run tests `./gradlew test`
2. To generate coverage `./gradlew jacocoTestReport`, generated html file location
   `build/jacocoHtml/index.html`
3. To tryout new scenarios, use main function or just write tests in (Following the other examples)
   `src/test/java/org/example/RecommendationEngineTest.java`

## Usage
1. To tryout new scenarios, use main function.
   * Create a `RecommendationEngine` object.
   * Call `getRestaurantRecommendations` method on it with user object and array of restaurants.
     * Please note, each restaurant object should have all the required fields.
     * Error handling has been skipped, as it was clear in the problem statement that all the required fields will be provided before using this engine.
## Author
  - Avdhesh