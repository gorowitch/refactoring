package kata.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.fail;

public class GuildedRoseTest {

    private PrintStreamCaptor captor;

    @BeforeEach
    public void setUp() {
        captor = new PrintStreamCaptor();

    }

    @Test
    public void goldenMaster() {
        generatePrices(captor.out(), new String[] {});

        Assertions.assertThat(captor.captured()).isEqualTo(resourceContent("guilded-rose-golden-master.txt"));
    }

    @Test
    public void goldenMasterFiveDays() {
        generatePrices(captor.out(), new String[] { "5"});

        Assertions.assertThat(captor.captured()).isEqualTo(resourceContent("guilded-rose-golden-master-five-days.txt"));
    }

    static void generatePrices(PrintStream out, String[] args) {
        out.println("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            out.println("-------- day " + i + " --------");
            out.println("name, sellIn, quality");
            for (Item item : items) {
                out.println(item);
            }
            out.println();
            app.updateQuality();
        }
    }

    private static class PrintStreamCaptor {
        private final PrintStream out;
        private final ByteArrayOutputStream bos;

        public PrintStreamCaptor() {
            bos = new ByteArrayOutputStream();
            out = new PrintStream(bos);
        }

        public PrintStream out() {
            return out;
        }

        public String captured() {
            return bos.toString();
        }
    }

    private static String resourceContent(final String resource) {
        try {
            Path path = Paths.get(GuildedRoseTest.class.getClassLoader().getResource(resource).toURI());
            return new String(Files.readAllBytes(path));
        } catch (Exception kaboom) {
            return fail(String.format("Unable to open resource: %s", resource),kaboom);
        }
    }

}
