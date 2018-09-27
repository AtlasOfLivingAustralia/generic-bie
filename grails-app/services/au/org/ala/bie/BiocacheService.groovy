package au.org.ala.bie

import javax.annotation.PostConstruct


class BiocacheService {

    def grailsApplication
    def webService

    String BIOCACHE_SERVICE_BACKEND_URL

    @PostConstruct
    def init() {
        BIOCACHE_SERVICE_BACKEND_URL = grailsApplication.config.biocacheService.internal.url
    }


    /**
     * Retrieve the available sounds for this taxon.
     *
     * @param taxonName
     * @return
     */
    def getSoundsForTaxon(taxonName){
        if(taxonName == null) {
            return []
        }

        def queryUrl = "${BIOCACHE_SERVICE_BACKEND_URL}/occurrences/search?q=${java.net.URLEncoder.encode(taxonName, "UTF-8")}&fq=multimedia:\"Sound\""
        def data = webService.getJson(queryUrl)
        //log.debug "sound data => " + data
        if(data.size() && data.has("occurrences") && data.get("occurrences").size()) {
            def recordUrl = "${BIOCACHE_SERVICE_BACKEND_URL}/occurrence/${data.get('occurrences').get(0).uuid}"
            webService.getJson(recordUrl)
        } else {
            []
        }
    }

    /**
     * Enum for image categories
     */
    public enum ImageCategory {
        TYPE, SPECIMEN, OTHER
    }
}
