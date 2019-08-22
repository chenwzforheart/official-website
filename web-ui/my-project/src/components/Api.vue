<template>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      prop="open_name"
      label="开放平台"
      width="180">
    </el-table-column>
    <el-table-column
      prop="industry"
      label="行业"
      width="180">
    </el-table-column>
    <el-table-column
      prop="portal"
      label="门户">
    </el-table-column>
    <el-table-column
      prop="api_doc"
      label="文档地址">
    </el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      tableData: []
    }
  },
  created () {
    let _this = this
    axios.get('/static/open_api.json').then(response => {
      _this.tableData = response.data.RECORDS
    })
  }
}
</script>
