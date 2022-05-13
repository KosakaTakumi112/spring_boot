package com.example.app;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.springframework.core.io.Resource;

//CSVのファイルを取得しDB操作のテストで使用できるようにするクラス
public class CsvDataSetLoader extends AbstractDataSetLoader {

  @Override
  protected IDataSet createDataSet(Resource resource) throws Exception {
    return new CsvURLDataSet(resource.getURL());
  }

}