package com.earth.testomania.technical.domain.model

import com.earth.testomania.R

enum class TechCategory(val strValue: String, val illustration: Int) {
    Linux("Linux", R.drawable.il_os),
    javaScript("JS", R.drawable.il_js),
    PHP("PHP", R.drawable.il_php),
    Networking("Networking", R.drawable.il_networking),
    Cloud("Cloud", R.drawable.il_cloud),
    Docker("Docker", R.drawable.il_docker),
    Kubernetes("Kubernetes", R.drawable.il_kubernetes),
    HTML("HTML", R.drawable.il_html),
    SQL("SQL", R.drawable.il_sql),
    wordPress("wordpress", R.drawable.il_wordpress),
    UNKNOWN("unknown", R.drawable.il_unknown);

    companion object {
        fun findIllustrationByCategory(category: String) =
            values().find {
                category.contains(it.strValue, ignoreCase = true)
            } ?: UNKNOWN
    }
}
