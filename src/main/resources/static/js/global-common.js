(function(root){

    //Nginx通用请求地址：解决前后端分离跨域问题
    root.BaseUrl = 'http://localhost/';
    //获取单行数据：Bootstarp Table.js
    function findData_ (bootstrapTableObj,objectInTr) {
        //根据按钮所在位置寻找附近的tr
        var tr = $(objectInTr).closest("tr"); // find相反
        //获取UniqueID
        var uniqeid = $(tr).data("uniqueid"); // 取节点的 data-xxx 属性
        var row_data = $(bootstrapTableObj).bootstrapTable('getRowByUniqueId',uniqeid);
        return row_data;
    }
    //重命名函数
    root.bootstrapTable.findData = findData_;


}(this));