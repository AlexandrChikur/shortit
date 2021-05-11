<template>
  <div class="form-shortener-container df">
    <div class="slogan-container">Keep it shorter</div>
    <div class="flex-container">
      <input type="text" name="url" v-model="url" class="form-shortener-url-input" placeholder="Paste your link here">
      <input type="button" @click="shortenize" class="form-shortener-url-button" value="Short it :)">
    </div>
    <div class="flex-container">
      <alert v-if="error" :alert_content="error" />
    </div>
  </div>
</template>



<script>
import apiInstance from '@/api/instance'
import Alert from "@/components/Alert";

export default {
  name: "FormShortener",
  components: {Alert},
  data: function (){
    return {
      url: null,
      error: null,
    }
  },
  methods: {
    async shortenize() {
      if(this.url) {
        try {
          const response = (await this.$api.urls.getShorterUrl({url: this.url}))
          this.url = apiInstance.defaults.baseURL + 'r/' + response.data.newurl
          this.error = null
        } catch (error) {
          this.error = error.response.data
        }
      }
    }
  },
}
</script>