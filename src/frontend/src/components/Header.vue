<template>
  <header class="header-cls df">
    <div class="header-logo">
      <a href="/"><img src="../assets/imgs/header_logo.svg" alt="Shortit"></a>
    </div>
    <nav>
      <ul class="nav-links">
        <li><a :href="docs_url">API Docs</a></li>
      </ul>
    </nav>
  </header>
</template>

<script>
import apiInstance from "@/api/instance";

export default {
  name: "Header",
  data(){
    return {
      docs_url: apiInstance.defaults.baseURL + '/swagger-ui.html',
    }
  },
}
</script>
