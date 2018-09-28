package com.nse.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.nse.csv.CorporateAction;
public class CorporateActionProcess implements ItemProcessor< com.nse.csv.CorporateAction,com.nse.model.CorporateAction> {
    private static final Logger log = LoggerFactory.getLogger(CorporateAction.class);
    public com.nse.model.CorporateAction process(final com.nse.csv.CorporateAction AnimeDTO) throws Exception {
       
         com.nse.model.CorporateAction transformedAnimeDTO = new com.nse.model.CorporateAction(AnimeDTO);
        log.info("Converting (" + AnimeDTO + ") into (" + transformedAnimeDTO + ")");
        return transformedAnimeDTO;
    }
	

}
