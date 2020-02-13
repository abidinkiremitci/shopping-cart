package com.tr.trendyol;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = "name")
@Getter
public class Category
{

    String name;

    Category parentCategory;

    Set<Campaign> campaignList = new HashSet<>();

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
        campaignList.add(campaign);
    }
}
