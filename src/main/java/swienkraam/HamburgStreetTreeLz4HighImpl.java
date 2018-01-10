package swienkraam;

import net.jpountz.lz4.LZ4Factory;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class HamburgStreetTreeLz4HighImpl extends HamburgStreetTreeByteImpl {

    public static final LZ4Factory LZ4_FACTORY = LZ4Factory.fastestInstance();

    private int size;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeLz4HighImpl(final String gmlId,
                                        final String objectId,
                                        final int baumId,
                                        final String gattung,
                                        final String gattungLatein,
                                        final String gattungDeutsch,
                                        final String art,
                                        final String artLatein,
                                        final String artDeutsch,
                                        final int pflanzjahr,
                                        final String kronendurchmesser,
                                        final String kronendmzahl,
                                        final int stammumfang,
                                        final String stammumfangzahl,
                                        final String strasse,
                                        final String hausnummer,
                                        final short ortsteilNr,
                                        final LocalDate standBearbeitung,
                                        final String bezirk,
                                        final String srsName,
                                        final String srsDimension,
                                        final String pos) {
        super(gmlId, objectId, baumId, gattung, gattungLatein, gattungDeutsch, art, artLatein, artDeutsch, pflanzjahr,
                kronendurchmesser, kronendmzahl, stammumfang, stammumfangzahl, strasse, hausnummer, ortsteilNr,
                standBearbeitung, bezirk, srsName, srsDimension, pos);
    }

    @Override
    protected byte[] preSave(byte[] storage) {
        this.size = storage.length;
        return LZ4_FACTORY.highCompressor().compress(storage);
    }

    @Override
    protected byte[] retrieve() {
        return LZ4_FACTORY.fastDecompressor().decompress(super.retrieve(), size);
    }

}
