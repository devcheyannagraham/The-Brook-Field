import { Routes } from '@angular/router';
import { PublicationFormComponent } from './Forms/publication-form/publication-form.component';
import { AccessoryFormComponent } from './Forms/accessory-form/accessory-form.component';
import { PublicationItemsComponent } from './Components/Products/publication-items/publication-items.component';
import { PublicationsComponent } from './Components/Products/publications/publications.component';
import { PublicationItemFormComponent } from './Forms/publication-item-form/publication-item-form.component';
import { AccessoriesComponent } from './Components/Products/accessories/accessories.component';
import { AccessoryItemsComponent } from './Components/Products/accessory-items/accessory-items.component';
import { OrderFormComponent } from './Forms/order-form/order-form.component';
import { ShopComponent } from './Components/BfimsShop/shop/shop.component';
import { ShopPublicationDetailComponent } from './Components/BfimsShop/shop-publication-detail/shop-publication-detail.component';
import { ShopAccessoryDetailComponent } from './Components/BfimsShop/shop-accessory-detail/shop-accessory-detail.component';
import { PopularItemsComponent } from './Components/Reports/popular-items/popular-items.component';
import { LowInventoryComponent } from './Components/Reports/low-inventory/low-inventory.component';
import { RecentOrdersComponent } from './Components/Reports/recent-orders/recent-orders.component';
import { LandingPageComponent } from './Components/landing-page/landing-page.component';
import { LoginComponent } from './Components/Auth/login/login.component';
import { HomeComponent } from './Components/Auth/home/home.component';
import { AdminDashboardComponent } from './Components/Auth/admin-dashboard/admin-dashboard.component';
import { authenticatedUserGuard } from './authenticated-user.guard';
import { adminGuard } from './admin.guard';
import { UserManagementComponent } from './Components/Auth/user-management/user-management.component';

export const routes: Routes = [
  {
    path: 'publicationitemform/publication/:publicationId',
    component: PublicationItemFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publicationitemform/:pubItemId',
    component: PublicationItemFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publicationitemform',
    component: PublicationItemFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publicationform/:publicationId',
    component: PublicationFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publicationform',
    component: PublicationFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publication/:publicationId',
    component: PublicationItemsComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'publications',
    component: PublicationsComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'accessory/:accessId',
    component: AccessoryItemsComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'accessories',
    component: AccessoriesComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'accessoryform/:accessoryId',
    component: AccessoryFormComponent,
    canActivate: [adminGuard]
  },
  {
    path: 'accessoryform',
    component: AccessoryFormComponent,
    canActivate: [adminGuard]

  },
  {
    path: 'checkout',
    component: OrderFormComponent,
  },
  {
    path: 'shop',
    component: ShopComponent,
  },
  {
    path: 'shop/publication/:shopItemId',
    component: ShopPublicationDetailComponent,
  },
  {
    path: 'shop/accessory/:shopItemId',
    component: ShopAccessoryDetailComponent,
  },
  {
    path: 'report/popularitems',
    component: PopularItemsComponent
  },
  {
    path: 'report/lowinventory',
    component: LowInventoryComponent,
    canActivate: [adminGuard]

  },
  {
    path: 'report/recentorders',
    component: RecentOrdersComponent,
    canActivate: [authenticatedUserGuard]
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [authenticatedUserGuard]

  },
  {
    path: 'admindashboard',
    component: AdminDashboardComponent,
    canActivate: [adminGuard]

  },
  {
    path: 'users',
    component: UserManagementComponent,
    canActivate: [adminGuard]

  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      actionType: "login"
    }
  },
  {
    path: 'register',
    component: LoginComponent,
    data: {
      actionType: "register"
    }
  },
  {
    path: '',
    component: LandingPageComponent,
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: "/",
    pathMatch: 'full'
  },
];
