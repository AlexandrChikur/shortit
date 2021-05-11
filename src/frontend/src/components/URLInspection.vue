<template>
  <div class="url-inspection-container">
    <div class="header df">
      <div class="title">URL Inspector</div>
      <div class="times-redirections">
        Amount of redirections: {{records.length}}
      </div>
    </div>
    <div class="form df">
      <input type="text" name="url" v-model="url" class="form-shortener-url-input inspect-input" placeholder="Paste your link here">
      <input type="button" @click="inspect" class="form-shortener-url-button inspect-button" value="Inspect">
    </div>
    <div class="flex-container inspection-error df">
      <alert v-if="error" :alert_content="error" />
    </div>
    <div class="output df">
      <ul>
        <li class="df" v-for="(record, idx) in records" :key="idx">
          <div class="info">{{ record.ip }}</div>
          <div class="device-image">
            <img src="../assets/imgs/phone-device.svg" alt="" v-if="record.device === 'phone'">
            <img src="../assets/imgs/desktop-device.svg" alt="" v-else>
          </div>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>

import Alert from "@/components/Alert";
export default {
  name: "URLInspection",
  components: {Alert},
  data() {
    return {
      url: '',
      records: [],
      error: null,
    }
  },
  methods: {
    async inspect(){
      if(this.url) {
        try {
          const response = (await this.$api.urls.getUrlInfo({url: this.url})).data
          this.records = response.data
          console.log(this.records)
          this.error = null
        } catch (error) {
          this.error = error.response.data
        }
      }
    }
  }
}
</script>