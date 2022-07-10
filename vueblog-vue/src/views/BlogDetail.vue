<template>
  <div class="m-container">
    <Header></Header>
    <div>
      <h2>{{blog.title}}</h2>
      <div class="content markdown-body" v-html="blog.content">

      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import 'github-markdown-css/github-markdown.css'

export default {
  name: "BlogDetail",
  components: {Header},
  data() {
    return {
      blog: {}
    }
  },
  created() {
    this.getBlog();
  },
  methods: {
    getBlog() {
      const blogId = this.$route.params.blogId;
      this.$axios.get('/blog/' + blogId).then((r) => {
        this.blog = r.data.data;
        var MarkdownIt = require('markdown-it');
        var md = new MarkdownIt();
        var result = md.render(this.blog.content);
        this.blog.content = result
      })
    }
  }
}
</script>

<style scoped>

</style>