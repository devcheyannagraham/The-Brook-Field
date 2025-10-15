import { test, expect } from '@playwright/test';
import {url} from './port';

// limited by inventory, if fails, reset db and try again

test("customer completes order", async ({ page }) => {
  await page.goto(url);

  //add items to cart
  await page.getByRole('button', { name: 'Shop' }).click();
  await page.locator(".shop-section").nth(0).locator(".shop-item").first().locator('a').click();
  await page.locator("table").first().locator("tbody").locator("tr").first().locator("button").getByText("Purchase").click();
  await page.getByRole('button', { name: 'Shop' }).click();
  await page.locator(".shop-section").nth(1).locator(".shop-item").first().locator('a').click();
  await page.getByRole('button', { name: 'Purchase' }).click();
    await page.getByRole('button', { name: 'Shop' }).click();
  await page.locator(".shop-section").nth(2).locator(".shop-item").first().locator('a').click();
  await page.getByRole('button', { name: 'Purchase' }).click();
    await page.getByRole('button', { name: 'Shop' }).click();
  await page.locator(".shop-section").nth(3).locator(".shop-item").first().locator('a').click();
  await page.getByRole('button', { name: 'Purchase' }).click();


  //complete checkout order form
  await page.getByRole('link', { name: 'Checkout' }).click();
  await page.getByRole('textbox', { name: 'First Name*' }).click();
  await page.getByRole('textbox', { name: 'First Name*' }).fill('Playwright');
  await page.getByRole('textbox', { name: 'Last Name*' }).click();
  await page.getByRole('textbox', { name: 'Last Name*' }).fill('playwright');
  await page.getByRole('textbox', { name: 'Street address*' }).click();
  await page.getByRole('textbox', { name: 'Street address*' }).fill('123 play ave');
  await page.getByRole('textbox', { name: 'Street address*' }).press('Tab');
  await page.getByRole('textbox', { name: 'City*' }).fill(' wright');
  await page.getByRole('textbox', { name: 'City*' }).press('Tab');
  await page.getByRole('textbox', { name: 'State*' }).fill('USA');
  await page.getByRole('textbox', { name: 'ZipCode*' }).click();
  await page.getByRole('textbox', { name: 'ZipCode*' }).fill('12345e');
  await page.getByRole('textbox', { name: 'Country*' }).click();
  await page.getByRole('textbox', { name: 'Country*' }).fill('USA');
  await page.getByRole('textbox', { name: 'Email*' }).click();
  await page.getByRole('textbox', { name: 'Email*' }).fill('play@wright.com');
  await page.getByRole('textbox', { name: 'Email*' }).press('Tab');
  await page.getByRole('textbox', { name: 'Phone*' }).fill('123-456-7890');
  await page.getByRole('button', { name: 'Submit Order' }).click();
  await expect(page.getByText('CartTotal Items:0Total Price')).toBeVisible();
});
