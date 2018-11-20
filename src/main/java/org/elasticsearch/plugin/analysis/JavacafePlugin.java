package org.elasticsearch.plugin.analysis;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.index.analysis.autocomplete.JavacafeAutocompleteTokenizerFactory;
import org.elasticsearch.index.analysis.chosung.JavacafeChosungTokenFilterFactory;
import org.elasticsearch.index.analysis.eng2kor.JavacafeEng2KorConvertFilterFactory;
import org.elasticsearch.index.analysis.jamo.JavacafeJamoTokenFilterFactory;
import org.elasticsearch.index.analysis.kor2eng.JavacafeKor2EngConvertFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import static java.util.Collections.singletonMap;

/**
 * Javacafe에서 개발한 필터 리스트
 *
 * @author hrkim
 *
 */
public class JavacafePlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        
        
        // (1) 한글 초성 분석 필터
        extra.put("javacafe_chosung", JavacafeChosungTokenFilterFactory::new);
        
        // (2) 한글 자모 분석 필터
        extra.put("javacafe_jamo", JavacafeJamoTokenFilterFactory::new);
        
        // (3) 영한 오타 변환 필터
        extra.put("javacafe_eng2kor", JavacafeEng2KorConvertFilterFactory::new);
        
        // (4) 한영 오타 변환 필터
        extra.put("javacafe_kor2eng", JavacafeKor2EngConvertFilterFactory::new);

        // (5) 한글 스펠링 체크 필터
        extra.put("javacafe_spell", JavacafeJamoTokenFilterFactory::new);
                
        
        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        // (1) 자동완성 토크나이저
        return singletonMap("javacafe_jaso_tokenizer", JavacafeAutocompleteTokenizerFactory::new);
    }

}

