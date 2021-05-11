<template>
  <div id="main">
    <Header/>
    <MainSection/>
    <Footer/>
  </div>
</template>

<script>
import Header from "@/components/Header";
import MainSection from "@/components/MainSection";
import Footer from "@/components/Footer";
export default {
  name: "MainPage",
  components: {Footer, MainSection, Header}
}
</script>