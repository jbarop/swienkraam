package swienkraam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Java representation of an tree of the "Straßenbaumkataster" of Hamburg.
 *
 * See http://www.hamburg.de/strassenbaeume-online/
 * See http://suche.transparenz.hamburg.de/dataset/strassenbaumkataster-hamburg
 */
@JsonIgnoreProperties({"gml_parent_id", "geom"})
public abstract class HamburgStreetTree {

    @JsonProperty("id")
    abstract String getGmlId();

    @JsonProperty("OBJECTID")
    abstract String getObjectId();

    @JsonProperty("baumid")
    abstract int getBaumId();

    @JsonProperty("gattung")
    abstract String getGattung();

    @JsonProperty("gattung_latein")
    abstract String getGattungLatein();

    @JsonProperty("gattung_deutsch")
    abstract String getGattungDeutsch();

    @JsonProperty("art")
    abstract String getArt();

    @JsonProperty("art_latein")
    abstract String getArtLatein();

    @JsonProperty("art_deutsch")
    abstract String getArtDeutsch();

    @JsonProperty("pflanzjahr")
    abstract int getPflanzjahr();

    @JsonProperty("kronendurchmesser")
    abstract String getKronendurchmesser();

    @JsonProperty("kronendmzahl")
    abstract String getKronendmzahl();

    @JsonProperty("stammumfang")
    abstract int getStammumfang();

    @JsonProperty("stammumfangzahl")
    abstract String getStammumfangzahl();

    @JsonProperty("strasse")
    abstract String getStrasse();

    @JsonProperty("hausnummer")
    abstract String getHausnummer();

    @JsonProperty("ortsteil_nr")
    abstract short getOrtsteilNr();

    @JsonProperty("stand_bearbeitung")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    abstract LocalDate getStandBearbeitung();

    @JsonProperty("bezirk")
    abstract String getBezirk();

    @JsonProperty("srsName")
    abstract String getSrsName();

    @JsonProperty("srsDimension")
    abstract String getSrsDimension();

    @JsonProperty("pos")
    abstract String getPos();

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + "(gmlId=" + getGmlId()
                + ", objectId=" + getObjectId()
                + ", baumId=" + getBaumId()
                + ", gattung=" + getGattung()
                + ", gattungLatein=" + getGattungLatein()
                + ", gattungDeutsch=" + getGattungDeutsch()
                + ", art=" + getArt()
                + ", artLatein=" + getArtLatein()
                + ", artDeutsch=" + getArtDeutsch()
                + ", pflanzjahr=" + getPflanzjahr()
                + ", kronendurchmesser=" + getKronendurchmesser()
                + ", kronendmzahl=" + getKronendmzahl()
                + ", stammumfang=" + getStammumfang()
                + ", stammumfangzahl=" + getStammumfangzahl()
                + ", strasse=" + getStrasse()
                + ", hausnummer=" + getHausnummer()
                + ", ortsteilNr=" + getOrtsteilNr()
                + ", standBearbeitung=" + getStandBearbeitung()
                + ", bezirk=" + getBezirk()
                + ", srsName=" + getSrsName()
                + ", srsDimension=" + getSrsDimension()
                + ", pos=" + getPos()
                + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !(o instanceof HamburgStreetTree)) {
            return false;
        }

        final HamburgStreetTree that = (HamburgStreetTree) o;
        return getBaumId() == that.getBaumId()
                && getPflanzjahr() == that.getPflanzjahr()
                && getStammumfang() == that.getStammumfang()
                && getOrtsteilNr() == that.getOrtsteilNr()
                && Objects.equals(getGmlId(), that.getGmlId())
                && Objects.equals(getObjectId(), that.getObjectId())
                && Objects.equals(getGattung(), that.getGattung())
                && Objects.equals(getGattungLatein(), that.getGattungLatein())
                && Objects.equals(getGattungDeutsch(), that.getGattungDeutsch())
                && Objects.equals(getArt(), that.getArt())
                && Objects.equals(getArtLatein(), that.getArtLatein())
                && Objects.equals(getArtDeutsch(), that.getArtDeutsch())
                && Objects.equals(getKronendurchmesser(), that.getKronendurchmesser())
                && Objects.equals(getKronendmzahl(), that.getKronendmzahl())
                && Objects.equals(getStammumfangzahl(), that.getStammumfangzahl())
                && Objects.equals(getStrasse(), that.getStrasse())
                && Objects.equals(getHausnummer(), that.getHausnummer())
                && Objects.equals(getStandBearbeitung(), that.getStandBearbeitung())
                && Objects.equals(getBezirk(), that.getBezirk())
                && Objects.equals(getSrsName(), that.getSrsName())
                && Objects.equals(getSrsDimension(), that.getSrsDimension())
                && Objects.equals(getPos(), that.getPos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGmlId(), getObjectId(), getBaumId(), getGattung(), getGattungLatein(),
                getGattungDeutsch(), getArt(), getArtLatein(), getArtDeutsch(), getPflanzjahr(), getKronendurchmesser(),
                getKronendmzahl(), getStammumfang(), getStammumfangzahl(), getStrasse(), getHausnummer(),
                getOrtsteilNr(), getStandBearbeitung(), getBezirk(), getSrsName(), getSrsDimension(), getPos());
    }

}
