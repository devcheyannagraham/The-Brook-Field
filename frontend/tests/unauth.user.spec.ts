import { test, expect } from '@playwright/test';
import {url} from './port';

const username = `user_${Date.now()}@example.com`;

test("unauth user cannot access authorized routes", async ({ page }) => {
  await page.goto(url);

  //register regular user
  await page.getByRole('link', { name: 'Register' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username);
  await page.locator('input[type="email"]').press('Tab');
  await page.getByRole('textbox').nth(1).fill('user@mail.com');
  await page.getByRole('textbox').nth(1).press('Tab');
  await page.locator('form div').filter({ hasText: 'Confirm Password' }).getByRole('textbox').fill('user@mail.com');
  await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
  await expect(page.getByText(`Welcome, ${username.toUpperCase()}`)).toBeVisible();

  // access admin publication routes
  await page.goto(`${url}/publications`);
  await expect(page.getByText('Unauthorized')).toBeVisible();
  await page.goto(`${url}/publicationform`);
  await expect(page.getByText('Unauthorized')).toBeVisible();

  // access admin accessory routes
  await page.goto(`${url}/accessories`);
  await expect(page.getByText('Unauthorized')).toBeVisible();
  await page.goto(`${url}/accessoryform`);
  await expect(page.getByText('Unauthorized')).toBeVisible();

  //access admin dashboard 
  await page.goto(`${url}/admindashboard`);
  await expect(page.getByText('Unauthorized')).toBeVisible();


  // access inventory report route
  await page.goto(`${url}/report/lowinventory`);
  await expect(page.locator('#toaster')).toBeVisible();
  await page.getByText('Unauthorized').click();

  // access recent order report routes
  await page.goto(`${url}/report/recentorders`);
  await expect(page.locator('#toaster')).toBeVisible();
  await page.getByText('Unauthorized').click();

  // access rpopular item report route
  await page.goto(`${url}/report/popularitems`);
  await expect(page.locator('#toaster')).toBeVisible();
  await page.getByText('Unauthorized').click();


  // access admin users route
  await page.goto(`${url}/users`);
  await expect(page.locator('#toaster')).toBeVisible();
  await page.getByText('Unauthorized').click();


});
