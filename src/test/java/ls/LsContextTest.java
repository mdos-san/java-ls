package ls;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class LsContextTest {
    LsContext context;

    @Before
    public void Init() {
        this.context = new LsContext();
    }

    @Test
    public void shouldHaveFilenames() {
        assertNotNull(this.context.fileNames);
    }
}