<template>
  <div class="hello">
    <HeadBox></HeadBox>
    <Api></Api>
    <ul>
      <li>
        <p>
          &copy; 2019 - 2020 CwzSmile All Right Reserved.
        </p>
      </li>
      <li>
        <a
          href="/"
          target="_self"
        >
          传播说明
        </a>
      </li>
      <li>
        <a
          href="/"
          target="_self"
        >
          运营细则
        </a>
      </li>
      <li>
        <a
          href="/"
          target="_self"
        >
          联系方式
        </a>
      </li>
      <li>
        <a
          href="/"
          target="_self"
        >
          投诉建议
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import HeadBox from './HeadBox'
import Api from './Api.vue'
export default {
  name: 'HelloWorld',
  components: {
    HeadBox,
    Api
  },
  data () {
    return {
      msg: 'Welcome to Your Api world'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  text-decoration: none;
  color: #9e9f9f;
}
</style>
