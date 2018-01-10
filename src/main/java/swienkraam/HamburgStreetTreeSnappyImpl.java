package swienkraam;

import org.iq80.snappy.Snappy;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class HamburgStreetTreeSnappyImpl extends HamburgStreetTreeByteImpl {

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeSnappyImpl(final String gmlId,
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
        return Snappy.compress(super.preSave(storage));
    }

    @Override
    protected byte[] retrieve() {
        final byte[] data = super.retrieve();
        return Snappy.uncompress(data, 0, data.length);
    }

}
