// 一个简单的对象,单层结构,不包含子对象,子数组
const simplestJavaData = {
    booleanType: true,
    floatType: 12.3,
    intType: 1234560,
    stringType: "字符串",

};
// 一个相对复杂的对象,包含子对象,子数组
const simpleJavaData = {
    simpleBasicDataType: {
        basicBoolean: true,
        basicFloat: 12.3,
        basicInt: 1234560
    },
    simpleWrapDataType: {
        wrapBoolean: true,
        wrapFloat: 12.3,
        wrapInteger: 1234560
    },
    stringValue: "字符串类型"
};

/**
 *判断 对象是否相同
 * @param object1 已知对象
 * @param object2   待检验对象
 * @returns {boolean}
 */
function isEqual(object1, object2) {
    if (object1.stringType !== undefined) {
        // 单层对象
        return object1.booleanType === object2.booleanType &&
            object1.floatType === object2.floatType &&
            object1.intType === object2.intType &&
            object1.stringType === object2.stringType;
    } else {
        // 复杂对象
        return object1.stringValue === object2.stringValue &&
            object1.simpleBasicDataType.basicBoolean === object2.simpleBasicDataType.basicBoolean &&
            object1.simpleBasicDataType.basicFloat === object2.simpleBasicDataType.basicFloat &&
            object1.simpleBasicDataType.basicInt === object2.simpleBasicDataType.basicInt &&
            object1.simpleWrapDataType.wrapBoolean === object2.simpleWrapDataType.wrapBoolean &&
            object1.simpleWrapDataType.wrapFloat === object2.simpleWrapDataType.wrapFloat &&
            object1.simpleWrapDataType.wrapInteger === object2.simpleWrapDataType.wrapInteger;

    }
}
