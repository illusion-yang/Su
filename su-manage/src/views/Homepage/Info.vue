<template>
  <div>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{userCount}}</div>
                            <div>用户总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{MfileCount}}</div>
                            <div>音效资源总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{VfileCount}}</div>
                            <div>影视资源总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                        <div class="grid-cont-center">
                            <div class="grid-num">{{discussCount}}</div>
                            <div>话题数量</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">音效资源比例</h3>
                <div style="background-color:white">
                    <ve-histogram :data="music" :theme="options"></ve-histogram>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">影视资源比例</h3>
                <div style="background-color:white">
                    <ve-pie :data="video" :theme="options"></ve-pie>
                </div>
            </el-col>
        </el-row>
        
    </div>
</template>

<script>
import request from '@/utils/request';
export default {
  data(){
    return{
      userCount: 0,
      MfileCount: 0,
      VfileCount: 0,
      discussCount: 0,
      music:{           
                columns: ['类别','总数'],
                rows: [
                    {'类别': '实时音效','总数': 0},
                    {'类别': '影视音效','总数': 0},
                    {'类别': '免费商用','总数': 0},
                    {'类别': '音效作品','总数': 0}
                ]
            },
            options: {
                color: ['#87cefa','#ffc0cb'],
            },
      video:{           
                columns: ['类别','总数'],
                rows: [
                    {'类别': '高清实拍','总数': 0},
                    {'类别': '短视频','总数': 0},
                    {'类别': '后期特效','总数': 0},
                    {'类别': '免费商用','总数': 0}
                ]
            },
            options: {
                color: ['#87cefa','#ffc0cb']
            },
    }
  },
  mounted(){
    this.getUser();
    this.getMusic();
    this.getVideo();
    this.getDiscuss();
  },
  methods:{
    getUser(){
      request.get("user/all").then(res=>{
        this.userCount = res.data.length;
      })
    },
    getDiscuss(){
        request.get("discuss/files").then(res=>{
        this.discussCount = res.data.length;
      })
    },
    getMusic(){
      request.get("music/files").then(res=>{
        this.MfileCount = res.data.length
        for(let item of res.data){
          this.getByMStyle(item.type)
        }
      })
    },
    getVideo(){
        request.get("video/files").then(res=>{
            console.log(res.data)
        this.VfileCount = res.data.length
        for(let item of res.data){
          this.getByVStyle(item.type)
        }
      })
    },
    getByVStyle(type){
      for(let item of this.video.rows){
        if(type.includes(item['类别'])){
          item['总数']++;
        }
      }
    },
    getByMStyle(type){
      for(let item of this.music.rows){
        if(type.includes(item['类别'])){
          item['总数']++;
        }
      }
    }
  }
}
</script>

<style scoped>
.grid-content {
    display: flex;
    align-items: center;
    height: 50px;
}

.grid-cont-center {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: darkgray;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}
</style>