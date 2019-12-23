package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.LanguageMapper;
import com.icicle.masterdb.model.Language;
import com.icicle.masterdb.service.LanguageService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-07-05 16:28:07.
*/
@Service
public class LanguageServiceImpl extends AbstractService<Language> implements LanguageService {
    @Resource
    private LanguageMapper languageMapper;
}