<template>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      img-height="200"
      background="#FFF"
      class="carousel slide carousel-vertical"
    >
      <b-carousel-slide v-for="(o, i) in list" :key="i">
        <template #img>
		  <router-link class="swiper_notice" :to="'/notice/details?notice_id='+o.notice_id">
			  <p class="title">{{o.title}}</p>
			  <p class="content" v-html="o.content">
			  </p>
			</router-link>
        </template>
      </b-carousel-slide>
    </b-carousel>
</template>


<script>
export default {
  props: {
    list: {
      type: Array,
      default: function () {
        return [];
      },
    },
    // vm: {
    //   type: Object,
    //   default: function () {
    //     return {
    //       title: "title",
    //     };
    //   },
    // },
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
  },
};
</script>


<style scoped>

  .title {
    padding: 0.65rem 0;
    height: 2.5rem;
    margin: 0 1.5rem;
    font-size: 1.2rem;
    font-weight: bold;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
  }

  .content {
    color: #6a737d;
    padding: 0.5rem 1.5rem;
    text-align: left;
    height: 12.25rem;
    font-size: 1rem;
    overflow: hidden;
  }

  .content:before {
    position: absolute;
    content: '';
    width: 100%;
    height: 2rem;
    left: 0;
    bottom: 0;
    background-color: rgba(255,255,255,.6);
  }

  .content >>> p {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    /*-webkit-line-clamp: 3;*/
    overflow: hidden;
  }

  .carousel-fade .carousel-inner .carousel-item {
	-webkit-transform: translateX(0);
	transform: translateX(0);
	transition-property: opacity;
}
.carousel-fade .carousel-inner .carousel-item,
.carousel-fade .carousel-inner .active.carousel-item-left,
.carousel-fade .carousel-inner .active.carousel-item-right {
	opacity: 0;
}
.carousel-fade .carousel-inner .active,
.carousel-fade .carousel-inner .carousel-item-next.carousel-item-left,
.carousel-fade .carousel-inner .carousel-item-prev.carousel-item-right {
	opacity: 1;
}

.carousel-vertical .carousel-inner .carousel-item-next.carousel-item-left,
.carousel-vertical .carousel-inner .carousel-item-prev.carousel-item-right {
	-webkit-transform: translateY(0);
	transform: translateY(0);
}
.carousel-vertical .carousel-inner .active.carousel-item-left,
.carousel-vertical .carousel-inner .carousel-item-prev {
	-webkit-transform: translateY(-100%);
	transform: translateY(-100%);
}
.carousel-vertical .carousel-inner .active.carousel-item-right,
.carousel-vertical .carousel-inner .carousel-item-next {
	-webkit-transform: translateY(100%);
	transform: translateY(100%);
}
</style>
