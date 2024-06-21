import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Observable} from "rxjs";
import {Route, Router} from "@angular/router";
import {AppStateService} from "../services/app-state.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{

  constructor(private productService:ProductService,
              private router :Router ,public  appState :AppStateService) {
  }
  ngOnInit() {
    //request asynchrone : on attend pas le retour
    this.searchProducts();
  }
  searchProducts(){
    /*this.appState.setProductState({
      status:"LOADING"
    });*/
    this.productService.searchProducts(this.appState.productState.keyword,
      this.appState.productState.currentPage,
      this.appState.productState.pageSize)
      .subscribe({
        next:(resp) =>{
          let products=resp.body as Product[];
          let totalProducts:number=parseInt(resp.headers.get('x-total-count')!);
          //this.appState.productState.totalProducts=totalProducts;
          let totalPages=Math.floor(totalProducts/this.appState.productState.pageSize);
          if (totalProducts % this.appState.productState.pageSize !=0){
            ++totalPages;
          }
          this.appState.setProductState({
            products : products ,
            totalProducts : totalProducts,
            totalPages:totalPages,
            status:"LOADED"
          })
        },
        error :err => {
          this.appState.setProductState({
            status:"ERROR",
            errorMessage:err
          })
        }
      })
    //this.products$=this.productService.getProducts();
  }

  handleCheckProduct(product: Product): void {
    this.productService.checkProducts(product).subscribe({
      next:updatedProduct => {
        product.checked = !product.checked;
        //this.getProducts();
      }
    })
  }

  handleDelete(product: Product) {
    if(confirm("Are you sure ?"))
      this.productService.deleteProduct(product).subscribe({
        next:value => {
          //this.getProducts();
          //this.appState.productState.products=
          //this.appState.productState.products.filter((p:any)=>p.id!=product.id);
          this.searchProducts();//pour que lorsqu'on supprime un produit , le nombe Total dans le dashboard se met à jour immédiatement
        }
      })
  }
  handleGotoPage(page: number) {
    this.appState.productState.currentPage=page;
    this.searchProducts();
  }

  handleEdit(product: Product) {
    this.router.navigateByUrl(`/admin/editProduct/${product.id}`)
  }

  protected readonly ProductService = ProductService;
}
