- 关于`application/x-www-form-urlencoded`
如果数据是简单、平面的`key-value`键值对，那么使用简单实用，不需要额外的编解码
如果数据是复杂的嵌套关系，有多层数据，那么使用`application/json`会简化数据的处理

如果复杂数据强行使用`application/x-www-form-urlencoded`会出现以下情况:
`key-value`的`key`的格式不太正常,后台不能自动化处理,`key`如下
> 数据格式是Form Data
``` 
stringValue: 字符串类型
simpleBasicDataType[basicInt]: 1234560
simpleBasicDataType[basicFloat]: 12.3
simpleBasicDataType[basicBoolean]: true
simpleWrapDataType[wrapInteger]: 1234560
simpleWrapDataType[wrapFloat]: 12.3
simpleWrapDataType[wrapBoolean]: true
```
如果使用html的form表单自己指定key,可以让后端自动化解析
```
stringValue: 字符串类型
simpleBasicDataType.basicInt: 1234560
simpleBasicDataType.basicFloat: 12.3
simpleBasicDataType.basicBoolean: true
simpleWrapDataType.wrapInteger: 1234560
simpleWrapDataType.wrapFloat: 12.3
simpleWrapDataType.wrapBoolean: true
```
