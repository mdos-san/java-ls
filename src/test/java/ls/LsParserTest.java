package ls;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class LsParserTest {
    private LsParser parser;
    private LsContext ctx;
    private String[] param1 = { "toto", "tata" };
    private String[] param2 = { "-r", "toto", "tata" };
    private String[] param3 = { "-R" };

    @Before
    public void InitParser() {
        this.parser = new LsParser();
        this.ctx = new LsContext();
    }

    @Test
    public void shouldExist() {
        assertNotNull(this.parser);
    }

    @Test
    public void ParserTestCase1() {
        LsContext context = this.parser.parse(param1);
        assertEquals(context.fileNames.get(0), "tata");
        assertEquals(context.fileNames.get(1), "toto");
    }

    @Test
    public void ParserTestCase2() {
        LsContext context = this.parser.parse(param2);
        assertEquals(context.fileNames.get(0), "toto");
        assertEquals(context.fileNames.get(1), "tata");
    }

    @Test
    public void ParserTestCase3() {
        LsContext context = this.parser.parse(param3);
        assertEquals(context.fileNames.get(0), ".");
    }

    @Test
    public void LoadOptionTestCase1() {
        this.parser.loadOption(this.ctx, "-a");
        assertTrue(this.ctx.hidden);
    }

    @Test
    public void LoadOptionTestCase2() {
        this.parser.loadOption(this.ctx, "-r");
        assertTrue(this.ctx.reverse);
    }

    @Test
    public void LoadOptionTestCase3() {
        this.parser.loadOption(this.ctx, "-l");
        assertTrue(this.ctx.list);
    }

    @Test
    public void LoadOptionTestCase4() {
        this.parser.loadOption(this.ctx, "-R");
        assertTrue(this.ctx.recursive);
    }
}
