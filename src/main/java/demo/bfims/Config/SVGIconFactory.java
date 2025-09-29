package demo.bfims.Config;

import demo.bfims.Enums.AccessoryType;
import demo.bfims.Enums.PublicationItemType;

public final class SVGIconFactory {

    public static SVGIcon CreateAccessoryItemIcon(AccessoryType accessoryType) {
        return switch (accessoryType) {
            case AccessoryType.MUG ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M160-120v-60h640v60H160Zm151-140q-63 0-107-43.5T160-410v-430h660q24.75 0 42.38 17.62Q880-804.75 880-780v160q0 24.75-17.62 42.37Q844.75-560 820-560h-96v150q0 63-44 106.5T573-260H311Zm0-60h261.98q36.02 0 63.52-27.5T664-410v-370H220v370q0 35 28 62.5t63 27.5Zm413-300h96v-160h-96v160ZM311-320h-91 444-353Z", "mug-icon");

            case AccessoryType.BOOKMARK ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M200-120v-665q0-24 18-42t42-18h440q24 0 42 18t18 42v665L480-240 200-120Zm60-91 220-93 220 93v-574H260v574Zm0-574h440-440Z", "bookmark-icon");

            case AccessoryType.PEN ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "m480-522 42 42 249-249-42-42-249 249ZM180-180h42l258-258-42-42-258 258v42Zm362-238L418-542l198-198-30-30-234 234-43-43 228-228q25-25 49.5-25.5T637-807l23 23 45-45q11-11 25-11t25 11l73 73q11 11 11 26t-11 26L542-418ZM244-120H120v-124l298-298 124 124-298 298Z", "pen-icon");
        };


//    public SVGIcon PublicationItemIcon(PublicationItemType publicationItemType) {
//    }
//
//    public SVGIcon PublicationIcon() {
//
//    }
    }
}

