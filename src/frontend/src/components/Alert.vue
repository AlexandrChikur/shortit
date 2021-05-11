<template>
  <div class="alert-container">
      <div class="alert-msg">{{alert_content}}</div>
  </div>
</template>

<script>
import {validationMixin} from 'vuelidate'
export default {
  mixins: [validationMixin],
  name: "Alert",
  props: ['alert_content'],
}
</script>