import org.junit.Test;
 
import java.util.List;
 
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
 
public class MatchersCheatSheetTest {
 
    @Test
    public void checkValue() {
        assertThat(Math.abs(-42), is(42));
        assertThat(Math.abs(-42), is(equalTo(42)));
        assertThat("JUnit", equalTo("JUnit"));
 
        // this matches anything :-)
        assertThat("JUnit", anything());
    }
 
    @Test
    public void checkTypeOfObject() {
        String computedValue = "JUnit tutorial";
        // all are equivalent:
        assertThat(computedValue, instanceOf(String.class));
        assertThat(computedValue, is(instanceOf(String.class)));
        assertThat(computedValue, isA(String.class));
        assertThat(computedValue, any(String.class));
    }
 
    @Test
    public void checkIdentity() {
        List<Integer> numbers = asList(1, 2, 3);
        // check that object is the same instance (all are equivalent):
        assertThat(numbers, sameInstance(numbers));
        assertThat(numbers, is(sameInstance(numbers)));
        assertThat(numbers, theInstance(numbers));
        assertThat(numbers, is(theInstance(numbers)));
    }
 
    @Test
    public void checkWhatObjectIsNot() {
        String answer = "42";
        assertThat(1, not(answer));
        assertThat("abc", is(not(answer)));
        assertThat(answer, is(not(instanceOf(List.class))));
    }
 
    @Test
    public void checkNotNull() {
        // both are the same:
        assertThat("Hamcrest", notNullValue());
        assertThat("Matchers", not(nullValue()));
    }
 
    @Test
    public void checksOnStrings() {
        assertThat("JUnit tutorial", containsString("tut"));
        assertThat("Esperanto", startsWith("E"));
        assertThat("Esperanto", endsWith("o"));
    }
 
    @Test
    public void checkCollections() {
        assertThat(asList("JUnit", "Tutorial"),
                everyItem(containsString("t")));
 
        List<Integer> numbers = asList(1, 2, 3);
        assertThat(numbers, hasItem(equalTo(1)));
        assertThat(numbers, hasItems(equalTo(3), isA(Number.class)));
    }
 
    @Test
    public void descriptiveError() {
        int expected = 42;
        assertThat(1, describedAs("The number suppose to be %0",
                equalTo(expected),
                expected));
    }
 
    @Test
    public void combiningMatchers() {
        assertThat("Esperanto", both(startsWith("E"))
                .and(endsWith("o")));
 
        assertThat("JUnit", either(startsWith("a"))
                .or(endsWith("z"))
                .or(containsString("Tutorial"))
                .or(anything()));
 
        assertThat("JUnit", allOf(
                containsString("Unit"),
                startsWith("J"),
                endsWith("it")));
 
        assertThat(asList(1, 2, 3), anyOf(
                hasItem(2),
                instanceOf(Number.class)));
    }
}