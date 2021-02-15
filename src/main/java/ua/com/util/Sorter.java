package ua.com.util;

import ua.com.bean.SpecializationAccountDetailsBean;
import ua.com.entity.Locale;

import javax.servlet.http.HttpServletRequest;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    private Sorter() {
        // utility class
    }

    /**
     * Sorts given list depends on the given locale and sorting direction.
     * Then send opposite direction as a request attribute to the page.
     *
     * @param specAccDetailList to be sorted
     * @param sortDirName Either 'asc' or 'desc'
     */
    public static void sortSpecAccDetListByName(List<SpecializationAccountDetailsBean> specAccDetailList,
                                                Locale locale,
                                                String sortDirName,
                                                HttpServletRequest req) {
        Collator collator = Collator.getInstance(new java.util.Locale(locale.getName()));

        if (sortDirName == null || ASC.equals(sortDirName)) {
            if (locale == Locale.EN) {
                specAccDetailList.sort((o1, o2) -> collator.compare(o1.getNameEN(), o2.getNameEN()));
            }
            if (locale == Locale.UA) {
                specAccDetailList.sort((o1, o2) -> collator.compare(o1.getNameUA(), o2.getNameUA()));
            }
            req.setAttribute("sortDirName", "desc");
        }

        if (DESC.equals(sortDirName)) {
            if (locale == Locale.EN) {
                specAccDetailList.sort((o1, o2) -> collator.compare(o2.getNameEN(), o1.getNameEN()));
            }
            if (locale == Locale.UA) {
                specAccDetailList.sort((o1, o2) -> collator.compare(o2.getNameUA(), o1.getNameUA()));
            }
            req.setAttribute("sortDirName", "asc");
        }
    }
}
