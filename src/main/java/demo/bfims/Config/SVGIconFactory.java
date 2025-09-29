package demo.bfims.Config;

import demo.bfims.Enums.AccessoryType;
import demo.bfims.Enums.LiteraryType;
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
    }


    public static SVGIcon CreatePublicationItemIcon(PublicationItemType publicationItemType) {
        return switch (publicationItemType) {
            case PublicationItemType.BOOK ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M290-80q-53.86 0-91.93-38.07Q160-156.14 160-210v-540q0-53.86 38.07-91.93Q236.14-880 290-880h510v600q-26 0-43 21t-17 49q0 28 17 49t43 21v60H290Zm-70-240q15-10 32.5-15t37.5-5h30v-480h-30q-29.17 0-49.58 20.42Q220-779.17 220-750v430Zm160-20h360v-480H380v480Zm-160 20v-500 500Zm69.54 180H699q-9-15-14-33t-5-37q0-20 5-37.5t15-32.5H289.61q-28.61 0-49.11 20.42Q220-239.17 220-210q0 29 20.5 49.5t49.04 20.5Z", "book-icon");

            case PublicationItemType.JOURNAL ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M270-80q-45 0-77.5-29.64Q160-139.27 160-183v-564q0-36.57 22.12-65.43Q204.24-841.3 240-849l380-79v620l-360 77q-17.14 3.69-28.57 17.54Q220-199.62 220-183q0 19 15 31t35 12h470v-660h60v720H270Zm70-228 220-47v-499l-220 45v501Zm-60 12.81V-796l-20 4q-17 4-28.5 15.83Q220-764.35 220-747v471q9.06-5.2 19.03-9.1Q249-289 260-291l20-4.19ZM220-787v511-511Z", "journal-icon");

            case PublicationItemType.LITERARY_PIECE -> null;
        };
    }

    public static SVGIcon CreateLiteraryPieceIcon(LiteraryType literaryType) {
        return switch (literaryType) {
            case LiteraryType.POEM ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M160-372v-60h640v60H160Zm0 160v-60h640v60H160Zm0-316v-60h640v60H160Zm0-160v-60h640v60H160Z", "poem-icon");

            case LiteraryType.ESSAY ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M319-250h322v-60H319v60Zm0-170h322v-60H319v60ZM220-80q-24 0-42-18t-18-42v-680q0-24 18-42t42-18h361l219 219v521q0 24-18 42t-42 18H220Zm331-554v-186H220v680h520v-494H551ZM220-820v186-186 680-680Z", "essay-icon");

            case LiteraryType.STORY ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M480-160q-48-38-104-59t-116-21q-42 0-82.5 11T100-198q-21 11-40.5-1T40-234v-482q0-11 5.5-21T62-752q46-24 96-36t102-12q58 0 113.5 15T480-740v506q51-33 107-49.5T700-300q36 0 78.5 7t81.5 29v-505q9.89 3.75 19.44 7.87Q889-757 898-752q10 6 16 15.68 6 9.67 6 20.32v482q0 23-19.5 35t-40.5 1q-37-20-77.5-31T700-240q-60 0-116 21t-104 59Zm60-167v-353l260-260v387L540-327Zm-120 63v-439q-34-19-79-28t-81-9q-47 0-87.5 10T100-704.47V-264q35-17 75.5-26.5t85-9.5q44.5 0 84.5 9.5t75 26.5Zm0 0v-439 439Z", "story-icon");

            case LiteraryType.ARTICLE ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M277-279h275v-60H277v60Zm0-171h406v-60H277v60Zm0-171h406v-60H277v60Zm-97 501q-24 0-42-18t-18-42v-600q0-24 18-42t42-18h600q24 0 42 18t18 42v600q0 24-18 42t-42 18H180Zm0-60h600v-600H180v600Zm0-600v600-600Z", "article-icon");

            case LiteraryType.REVIEW ->
                    new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M560-574v-48q33-14 67.5-21t72.5-7q26 0 51 4t49 10v44q-24-9-48.5-13.5T700-610q-38 0-73 9.5T560-574Zm0 220v-49q33-13.5 67.5-20.25T700-430q26 0 51 4t49 10v44q-24-9-48.5-13.5T700-390q-38 0-73 9t-67 27Zm0-110v-48q33-14 67.5-21t72.5-7q26 0 51 4t49 10v44q-24-9-48.5-13.5T700-500q-38 0-73 9.5T560-464ZM248-300q53.57 0 104.28 12.5Q403-275 452-250v-427q-45-30-97.62-46.5Q301.76-740 248-740q-38 0-74.5 9.5T100-707v434q31-14 70.5-20.5T248-300Zm264 50q50-25 98-37.5T712-300q38 0 78.5 6t69.5 16v-429q-34-17-71.82-25-37.82-8-76.18-8-54 0-104.5 16.5T512-677v427Zm-30 90q-51-38-111-58.5T248-239q-36.54 0-71.77 9T106-208q-23.1 11-44.55-3Q40-225 40-251v-463q0-15 7-27.5T68-761q42-20 87.39-29.5 45.4-9.5 92.61-9.5 63 0 122.5 17T482-731q51-35 109.5-52T712-800q46.87 0 91.93 9.5Q849-781 891-761q14 7 21.5 19.5T920-714v463q0 27.89-22.5 42.45Q875-194 853-208q-34-14-69.23-22.5Q748.54-239 712-239q-63 0-121 21t-109 58ZM276-489Z", "review-icon");
        };
    }

    public static SVGIcon CreatePublicationIcon() {
        return new SVGIcon("http://www.w3.org/2000/svg", "0 -960 960 960", "M220-80q-24 0-42-18t-18-42v-680q0-24 18-42t42-18h520q24 0 42 18t18 42v680q0 24-18 42t-42 18H220Zm0-60h520v-680h-60v266l-97-56-97 56v-266H220v680Zm0 0v-680 680Zm266-414 97-56 97 56-97-56-97 56Z", "publication-icon");

    }
}

