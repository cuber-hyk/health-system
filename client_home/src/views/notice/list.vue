<template>
  <div class="page_notice" id="notice_list">
    <div class="warp">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="car_notice">
              <!-- 公告列表 -->
              <!--<div class="notice_list_title">
                  <span class="title">{{$page_title("/notice/list") || "公告列表"}}</span>
                </div>-->
              <div class="header-carousel">  
    <swiper :slides-per-view="1" :loop="true" :autoplay="{ delay: 1000, disableOnInteraction: true,stopOnLastSlide:false }">  
      <swiper-slide v-for="(slide, index) in slides" :key="index">  
        <img :src="slide.image" :alt="slide.title">  
          
      </swiper-slide>  
      
    <!--  <div class="swiper-pagination" slot="pagination"></div>  
      <div class="swiper-button-next" slot="button-next"></div>  
      <div class="swiper-button-prev" slot="button-prev"></div>  -->
    </swiper>  
  </div> 
              <div class="notice_list">
              
                
                         
                <router-link
                  class="notice_block"
                  v-for="(o, i) in list"
                  :key="i"
                  :to="'/notice/details?' + 'notice_id' + '=' + o.notice_id"
                >
                  <div class="notice_title ellipsis_2">
                    {{ o.title }}
                  </div>
                  <div class="notice_create_time">
                    {{ o.create_time| formatDate }}
                  </div>
                </router-link>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <!-- 分页器 -->
          <div class="col overflow-auto flex_cc">
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";


export default {
  mixins: [mixin],
  data() {
    return {
      url_get_list: "~/api/notice/get_list",
      list: [],
      query: {
        title: "",
        page: 1,
        size: 10,
      },
      // 页面数
      count_pages: 3,
            slides: [  
        { image: '/img/gg.jpg', title: 'Title 1' },  
        { image: '/img/lianxiwomen.png', title: 'Title 2' },  
        { image: '/img/default.png', title: 'Title 3' }  
      ] 
    };
  },
  methods: {
    /**
     * 获取公告后
     * @param {Object} json 响应对象
     */
    get_list_after(json) {
      this.count_pages = Math.ceil(json.result.count / this.query.size);
    },
    goToPage(v){
      this.query.page = v;
      this.goToNew(v)
    }
  },
};
</script>

<style scoped>
.header-carousel {  
  width: 100%;  
  height: 300px; 
  overflow: hidden;  
  position: relative;  
}  
  
.header-carousel img {  
  width: 100%;  
  height: 100%;  
  object-fit: cover;  
}  
.flex_cc {
  display: flex;
  justify-content: center;
  align-items: center;
}
.ellipsis_2 {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.container {
  min-height: 800px;
}
.notice_block {
  display: grid;
  justify-content: space-around;
  padding: 10px;
  margin: 25px;
}
.notice_title {
  text-overflow: ellipsis;
  white-space: nowrap;
}
  .notice_list_title{
    margin: 0 0.5rem;
    background-color: #d2d2d2;
    color: white;
    display: flex;
    height: 4rem;
    align-items: center;
    justify-content: space-between;
    border-radius: 0.5rem;
  }
.title {
  font-size: 1.5rem;
  font-weight: bold;
  padding-left: 1rem;
}
</style>
