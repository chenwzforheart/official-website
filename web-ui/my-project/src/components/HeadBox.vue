<template>
  <div class="headbox">
    <img src="../assets/title.svg">
    <span>{{ msg }}</span>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Api world'
    }
  }
}
</script>

<style scoped>
div {
  display: inline;
}
span {
  font-size: 24px;
}
</style>
