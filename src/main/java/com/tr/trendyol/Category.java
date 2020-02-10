package com.tr.trendyol;

import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = "name")
public class Category
{

    String name;

    Category parentCategory;

    Set<Campaign> campaignList;

    public Category(String name)
    {
        this.name = name;
    }

    public Category(String name, Category parentCategory)
    {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public void addCampaign(Campaign campaign)
    {
        if (campaignList == null)
        {
            campaignList = new HashSet<Campaign>();
        }
        campaignList.add(campaign);
    }
}
