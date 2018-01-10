package swienkraam;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

/**
 * Implementation of {@link HamburgStreetTree} as a classic Java object.
 */
final public class HamburgStreetTreeObjectImpl extends HamburgStreetTree {

    private final String gmlId;
    private final String objectId;
    private final int baumId;
    private final String gattung;
    private final String gattungLatein;
    private final String gattungDeutsch;
    private final String art;
    private final String artLatein;
    private final String artDeutsch;
    private final int pflanzjahr;
    private final String kronendurchmesser;
    private final String kronendmzahl;
    private final int stammumfang;
    private final String stammumfangzahl;
    private final String strasse;
    private final String hausnummer;
    private final short ortsteilNr;
    private final LocalDate standBearbeitung;
    private final String bezirk;
    private final String srsName;
    private final String srsDimension;
    private final String pos;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeObjectImpl(final String gmlId,
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
        this.gmlId = gmlId;
        this.objectId = objectId;
        this.baumId = baumId;
        this.gattung = gattung;
        this.gattungLatein = gattungLatein;
        this.gattungDeutsch = gattungDeutsch;
        this.art = art;
        this.artLatein = artLatein;
        this.artDeutsch = artDeutsch;
        this.pflanzjahr = pflanzjahr;
        this.kronendurchmesser = kronendurchmesser;
        this.kronendmzahl = kronendmzahl;
        this.stammumfang = stammumfang;
        this.stammumfangzahl = stammumfangzahl;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.ortsteilNr = ortsteilNr;
        this.standBearbeitung = standBearbeitung;
        this.bezirk = bezirk;
        this.srsName = srsName;
        this.srsDimension = srsDimension;
        this.pos = pos;
    }

    public String getGmlId() {
        return this.gmlId;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public int getBaumId() {
        return this.baumId;
    }

    public String getGattung() {
        return this.gattung;
    }

    public String getGattungLatein() {
        return this.gattungLatein;
    }

    public String getGattungDeutsch() {
        return this.gattungDeutsch;
    }

    public String getArt() {
        return this.art;
    }

    public String getArtLatein() {
        return this.artLatein;
    }

    public String getArtDeutsch() {
        return this.artDeutsch;
    }

    public int getPflanzjahr() {
        return this.pflanzjahr;
    }

    public String getKronendurchmesser() {
        return this.kronendurchmesser;
    }

    public String getKronendmzahl() {
        return this.kronendmzahl;
    }

    public int getStammumfang() {
        return this.stammumfang;
    }

    public String getStammumfangzahl() {
        return this.stammumfangzahl;
    }

    public String getStrasse() {
        return this.strasse;
    }

    public String getHausnummer() {
        return this.hausnummer;
    }

    public short getOrtsteilNr() {
        return this.ortsteilNr;
    }

    public LocalDate getStandBearbeitung() {
        return this.standBearbeitung;
    }

    public String getBezirk() {
        return this.bezirk;
    }

    public String getSrsName() {
        return this.srsName;
    }

    public String getSrsDimension() {
        return this.srsDimension;
    }

    public String getPos() {
        return this.pos;
    }

}
