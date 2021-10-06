/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function generateColor(){
    let r = parseInt(Math.random()*255)
    let g = parseInt(Math.random()*255)
    let b = parseInt(Math.random()*255)
    return `rgb(${r}, ${g}, ${b})`
}

function cateChart(id, cateLabels=[], cateInfo=[]){
    
    let colors=[]
    for(let i = 0; i < cateInfo.length; i++)
        colors.push(generateColor())
    
    const data = {
  labels: cateLabels,
  datasets: [{
    label: 'Thong ke san pham theo danh muc',
    data: cateInfo,
    backgroundColor: colors,
    hoverOffset: 4
    }]
  };

    const config = {
        type: 'doughnut',
        data: data,
      };
      
    let ctx = document.getElementById(id).getContext("2d");
    new Chart(ctx, config)
    
}

function productChart(id, productLabels=[], productInfo=[] ){
      
    let colors=[]
    for(let i = 0; i < productInfo.length; i++)
        colors.push(generateColor())
    
    const data = {
  labels: productLabels,
  datasets: [{
    label: 'Thong doanh thu theo san pham',
    data: productInfo,
    backgroundColor: colors,
    hoverOffset: 4,
    borderColor: 'rgb(75, 192, 192)'
    }]
  };

    const config = {
        type: 'line',
        data: data,
      };
      
    let ctx = document.getElementById(id).getContext("2d");
    new Chart(ctx, config)  
}

function productMonthChart(id, productMonthLabels=[], productMonthInfo=[] ){
      
    let colors=[]
    for(let i = 0; i < productMonthInfo.length; i++)
        colors.push(generateColor())
    
    const data = {
  labels: productMonthLabels,
  datasets: [{
    label: 'Thong doanh thu theo thang',
    data: productMonthInfo,
    backgroundColor: colors,
    hoverOffset: 4,
    borderColor: 'rgb(75, 192, 192)'
    }]
  };

    const config = {
        type: 'bar',
        data: data,
      };
      
    let ctx = document.getElementById(id).getContext("2d");
    new Chart(ctx, config)  
}